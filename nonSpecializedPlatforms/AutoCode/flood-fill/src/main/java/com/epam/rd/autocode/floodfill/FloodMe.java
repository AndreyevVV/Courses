package com.epam.rd.autocode.floodfill;

public class                FloodMe implements FloodFill{

    String image;
    int height;
    int width;
    String land  = "█";
    String water = "░";

    @Override
    public void flood(String map, FloodLogger logger) {
        image = map;
        logger.log(image);

        String[] rows = image.split("\n");
        height = rows.length;
        width = rows[0].length();

        Point[][] matrix = toMatrix(image);

        floodMatrix(matrix);
        image = matrixToString(matrix);

        if (isFlooded(image)) logger.log(image);

        while (!isFlooded(image)) {
            flood(image, logger);
        }
    }

    private void floodMatrix(Point[][] matrix) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j].value.equals(water) && !matrix[i][j].preFlooded) {
                    floodUtil(matrix,i + 1,j);
                    floodUtil(matrix,i - 1,j);
                    floodUtil(matrix,i,j + 1);
                    floodUtil(matrix,i,j - 1);
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j].preFlooded = false;
            }
        }
    }

    private void floodUtil(Point[][] matrix, int i, int j) {
        if (j < 0 || j >= width || i < 0 || i >= height)
            return;
        if (matrix[i][j].value.equals(land)) {
            matrix[i][j].value = water;
            matrix[i][j].preFlooded = true;
        }
    }


    public boolean isFlooded(String map) {
        map = map.replaceAll("\n", "");
        String[] characters = map.split("");
        for (String str :
                characters) {
            if (!str.equals("░")) return false;
        }
        return true;
    }

    private Point[][] toMatrix(String inMatrix) {
        Point[][] matrix = new Point[height][width];
        String[] rows = inMatrix.split("\n");
        for (int i = 0; i < height; i++) {
            String[] row = rows[i].split("");
            for (int j = 0; j < width; j++) {
                matrix[i][j] = new Point(row[j], false);
            }
        }
        return matrix;
    }

    private String matrixToString(Point[][] matrix) {
        StringBuilder reMap = new StringBuilder();
        StringBuilder reRow;
        for (int i = 0; i < height; i++) {
            reRow = new StringBuilder();
            for (int j = 0; j < width; j++) {
                reRow.append(matrix[i][j].value);
            }
            if (i < height - 1) {
                reRow.append("\n");
            }
            reMap.append(reRow);
        }
        return reMap.toString();
    }
    
    public static class Point {
        public String value;
        public boolean preFlooded;

        public Point(String value, boolean preFlooded) {
            this.value = value;
            this.preFlooded = preFlooded;
        }
    }
}
