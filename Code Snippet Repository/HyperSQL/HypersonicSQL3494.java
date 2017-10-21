    private static void testSection(Statement stat, HsqlArrayList section,
                                    String scriptName, int line) {

        //create an appropriate instance of ParsedSection
        ParsedSection pSection = parsedSectionFactory(section);

        if (pSection == null) {    //it was not possible to sucessfully parse the section
            System.out.println(
                "The section starting at " + scriptName + ':' + line
                + " could not be parsed, and so was not processed." + LS);

            return;
        }

        if (pSection instanceof IgnoreParsedSection) {
            System.out.println("At " + scriptName + ':' + line + ": "
                               + pSection.getResultString());

            return;
        }

        if (pSection instanceof DisplaySection
                || pSection instanceof WaitSection
                || pSection instanceof ProceedSection) {
            String s = pSection.getResultString();

            if (s != null) {

                // May or may not want to report line number for these sections?
                System.out.println(pSection.getResultString());
            }
        }

        if (pSection instanceof DisplaySection) {
            return;    // Do not run test method for DisplaySections.
        }

        if (!pSection.test(stat)) {
            System.out.println("Section starting at " + scriptName + ':'
                               + line + " returned an unexpected result: "
                               + pSection.getTestResultString());

            if (TestUtil.abortOnErr) {
                throw new TestRuntimeException(scriptName + ": " + line
                                               + "pSection");
            }
        }
    }
