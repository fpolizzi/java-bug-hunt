package com.amigoscode.bughunt.easy.bug30;

import java.util.List;

public class ReportBuilder {

    private final String reportName;

    public ReportBuilder(String reportName) {
        this.reportName = reportName;
    }

    public String build(List<String> entries) {
        List<String> filtered = entries.stream()
                .filter(e -> e != null)
                .filter(e -> !e.isBlank())
                .map(String::trim)
                .toList();

        long count = filtered.size();
        String joined = String.join(", ", filtered);

        return reportName + " [" + count + "]: " + joined;
    }

    public String name() {
        return reportName;
    }
}
