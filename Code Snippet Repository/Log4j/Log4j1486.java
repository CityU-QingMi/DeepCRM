            public TextTable(Ansi ansi) {
                // "* -c, --create                Creates a ...."
                this(ansi, new Column[] {
                        new Column(2,                                        0, TRUNCATE), // "*"
                        new Column(2,                                        0, TRUNCATE), // "-c"
                        new Column(1,                                        0, TRUNCATE), // ","
                        new Column(optionsColumnWidth - 2 - 2 - 1       , 1, SPAN),  // " --create"
                        new Column(usageHelpWidth - optionsColumnWidth, 1, WRAP) // " Creates a ..."
                });
            }
