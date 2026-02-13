package core.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.io.FileUtils;

public final class AllureResultsManager {
    private static final Path PROJECT_DIR = Path.of(System.getProperty("user.dir"));
    private static final Path RESULTS_DIR = PROJECT_DIR.resolve("target").resolve("allure-results");
    private static final Path RESULTS_HISTORY_DIR = RESULTS_DIR.resolve("history");

    private static final List<Path> HISTORY_CANDIDATES = List.of(
            PROJECT_DIR.resolve("allure-report").resolve("history"),
            PROJECT_DIR.resolve("target").resolve("allure-report").resolve("history"),
            PROJECT_DIR.resolve("target").resolve("site").resolve("allure-maven").resolve("history"),
            PROJECT_DIR.resolve("target").resolve("site").resolve("allure-maven-plugin").resolve("history"));

    private static final Object LOCK = new Object();
    private static boolean prepared = false;

    private AllureResultsManager() {
    }

    public static void prepareAllureResultsDirectory() {
        if (prepared) {
            return;
        }

        synchronized (LOCK) {
            if (prepared) {
                return;
            }

            try {
                if (Files.exists(RESULTS_DIR)) {
                    FileUtils.deleteDirectory(RESULTS_DIR.toFile());
                }
                Files.createDirectories(RESULTS_DIR);

                Path previousHistory = findPreviousHistoryDirectory();
                if (previousHistory != null) {
                    FileUtils.copyDirectory(previousHistory.toFile(), RESULTS_HISTORY_DIR.toFile());
                }
                prepared = true;
            } catch (IOException e) {
                throw new RuntimeException("Unable to prepare Allure results directory", e);
            }
        }
    }

    private static Path findPreviousHistoryDirectory() {
        for (Path candidate : HISTORY_CANDIDATES) {
            if (Files.exists(candidate) && Files.isDirectory(candidate)) {
                return candidate;
            }
        }
        return null;
    }
}
