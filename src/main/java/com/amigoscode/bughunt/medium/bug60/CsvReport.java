package com.amigoscode.bughunt.medium.bug60;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Processes CSV data provided as an Iterator of lines.
 * Supports counting rows, extracting headers, and collecting all data rows.
 *
 * Typical usage:
 *   Iterator<String> lines = readCsvFile("report.csv");
 *   CsvReport report = new CsvReport(lines);
 *   int total = report.rowCount();
 *   List<String> headers = report.headers();
 *   List<List<String>> data = report.dataRows();
 */
public class CsvReport {

    private final List<String> rows;

    public CsvReport(Iterator<String> rows) {
        List<String> list = new ArrayList<>();
        rows.forEachRemaining(list::add);
        this.rows = List.copyOf(list);
    }

    /**
     * Returns the total number of rows (including the header row).
     * BUG: consumes the entire iterator — subsequent calls to
     * headers() or dataRows() see an exhausted iterator.
     */
    public int rowCount() {
        return rows.size();
    }

    /**
     * Returns the column headers (first row, split by comma).
     */
    public List<String> headers() {
        if (rows.isEmpty()) return List.of();
        return List.of(rows.get(0).split(","));
    }

    /**
     * Returns all data rows (everything after the header), each split by comma.
     */
    public List<List<String>> dataRows() {
        List<List<String>> dataRows = new ArrayList<>();
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
            String line = rows.get(rowIndex);
            dataRows.add(List.of(line.split(",")));
        }
        return dataRows;
    }

    /**
     * Returns the value at a specific row and column index (0-based, excluding header).
     */
    public String valueAt(int row, int col) {
        List<List<String>> data = dataRows();
        if (row < 0 || row >= data.size()) {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }
        List<String> rowData = data.get(row);
        if (col < 0 || col >= rowData.size()) {
            throw new IndexOutOfBoundsException("Col " + col + " out of bounds");
        }
        return rowData.get(col);
    }

    /**
     * Returns a summary: "N rows, M columns".
     */
    public String summary() {
        int rows = rowCount();
        List<String> hdrs = headers();
        return rows + " rows, " + hdrs.size() + " columns";
    }
}
