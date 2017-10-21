    public void addLineData(
        int inputStartLine,
        String inputFileName,
        int inputLineCount,
        int outputStartLine,
        int outputLineIncrement) {
        // check the input - what are you doing here??
        int fileIndex = filePathList.indexOf(inputFileName);
        if (fileIndex == -1) // still
            throw new IllegalArgumentException(
                "inputFileName: " + inputFileName);

        //Jasper incorrectly SMAPs certain Nodes, giving them an 
        //outputStartLine of 0.  This can cause a fatal error in
        //optimizeLineSection, making it impossible for Jasper to
        //compile the JSP.  Until we can fix the underlying
        //SMAPping problem, we simply ignore the flawed SMAP entries.
        if (outputStartLine == 0)
            return;

        // build the LineInfo
        LineInfo li = new LineInfo();
        li.setInputStartLine(inputStartLine);
        li.setInputLineCount(inputLineCount);
        li.setOutputStartLine(outputStartLine);
        li.setOutputLineIncrement(outputLineIncrement);
        if (fileIndex != lastFileID)
            li.setLineFileID(fileIndex);
        lastFileID = fileIndex;

        // save it
        lineData.add(li);
    }
