package com.techelevator.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TELog {
    private static final String LOG_FILE_BASE = "logs/search_";
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static PrintWriter pw = null;
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        try {
            if (pw == null) {
                String filename = LOG_FILE_BASE + LocalDate.now().format(DATE) + ".log";
                pw = new PrintWriter(new FileWriter(filename,true));
            }
            String stampedMessage = LocalDateTime.now().format(DATE_TIME) + "\n" + message;
            pw.println(stampedMessage);
            pw.flush();
        } catch (IOException e) {
            throw new TELogException("Error writing to log file", e);
        }
    }

    public static void closeLogger() {
        if (pw != null) {
            pw.close();
            pw = null; // Reset pw to null to ensure it can be reinitialized
        }
    }
}