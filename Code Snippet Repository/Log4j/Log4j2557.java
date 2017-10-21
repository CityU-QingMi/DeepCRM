    private void showRgbColorPalette() {
        int[] foregroundBackground = {38, 48};
        for (int fbg : foregroundBackground) {
            for (int r = 0; r < 2; r++) {
                System.out.println("RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB RGB ");
                for (int g = 0; g < 6; g++) {
                    for (int b = 0; b < 6; b++) {
                        int col = 16 + 36 * (0 + 3 * r) + 6 * g + b;
                        System.out.printf("\u001B[%d;5;%dm%d%d%d \u001B[0m", fbg, col, (0 + 3 * r),g,b);
                    }
                    for (int b = 0; b < 6; b++) {
                        int col = 16 + 36 * (1 + 3 * r) + 6 * g + b;
                        System.out.printf("\u001B[%d;5;%dm%d%d%d \u001B[0m", fbg, col, (1 + 3 * r),g,b);
                    }
                    for (int b = 0; b < 6; b++) {
                        int col = 16 + 36 * (2 + 3 * r) + 6 * g + b;
                        System.out.printf("\u001B[%d;5;%dm%d%d%d \u001B[0m", fbg, col, (2 + 3 * r),g,b);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println("RGB RGB RGB RGB RGB RGB");
            int r = 6;
            for (int g = 0; g < 4; g++) {
                for (int b = 0; b < 6; b++) {
                    int col = 16 + 36 * r + 6 * g + b;
                    System.out.printf("\u001B[%d;5;%dm%d%d%d \u001B[0m", fbg, col, r,g,b);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
