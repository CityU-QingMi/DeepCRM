    static void showUsage() {

        System.out.print("Usage: java CodeSwitcher paths|{--pathlist=listfile} "
                         + "[{+|-}label...] [+][-]\n"
                         + "If no labels are specified then all used\n"
                         + "labels in the source code are shown.\n"
                         + "Use +MODE to switch on the things labeld MODE\n"
                         + "Use -MODE to switch off the things labeld MODE\n"
                         + "Path: Any number of path or files may be\n"
                         + "specified. Use . for the current directory\n"
                         + "(including sub-directories).\n"
                         + "Example: java CodeSwitcher +JAVA2 .\n"
                         + "This example switches on code labeled JAVA2\n"
                         + "in all *.java files in the current directory\n"
                         + "and all subdirectories.\n");
    }
