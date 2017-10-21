    @AfterClass
    public static void after() {
        if (stringTime > paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times faster than StringFormat.",
                ((float) stringTime / paramTime)));
        } else if (stringTime < paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times slower than StringFormat.",
                ((float) paramTime / stringTime)));
        } else {
            System.out.println("The speed of Parameterized and StringFormat are the same");
        }
        if (msgFormatTime > paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times faster than MessageFormat.",
                ((float) msgFormatTime / paramTime)));
        } else if (msgFormatTime < paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times slower than MessageFormat.",
                ((float) paramTime / msgFormatTime)));
        } else {
            System.out.println("The speed of Parameterized and MessageFormat are the same");
        }
        if (formattedTime > paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times faster than Formatted.",
                ((float) formattedTime / paramTime)));
        } else if (formattedTime < paramTime) {
            System.out.println(String.format("Parameterized is %1$.2f times slower than Formatted.",
                ((float) paramTime / formattedTime)));
        } else {
            System.out.println("The speed of Parameterized and Formatted are the same");
        }
    }
