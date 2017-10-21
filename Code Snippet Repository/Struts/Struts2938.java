    public void optimizeLineSection() {

        //Incorporate each LineInfo into the previous LineInfo's
        //outputLineIncrement, if possible
        int i = 0;
        while (i < lineData.size() - 1) {
            LineInfo li = (LineInfo)lineData.get(i);
            LineInfo liNext = (LineInfo)lineData.get(i + 1);
            if (!liNext.lineFileIDSet
                && liNext.inputStartLine == li.inputStartLine
                && liNext.inputLineCount == 1
                && li.inputLineCount == 1
                && liNext.outputStartLine
                    == li.outputStartLine
                        + li.inputLineCount * li.outputLineIncrement) {
                li.setOutputLineIncrement(
                    liNext.outputStartLine
                        - li.outputStartLine
                        + liNext.outputLineIncrement);
                lineData.remove(i + 1);
            } else {
                i++;
            }
        }

        //Incorporate each LineInfo into the previous LineInfo's
        //inputLineCount, if possible
        i = 0;
        while (i < lineData.size() - 1) {
            LineInfo li = (LineInfo)lineData.get(i);
            LineInfo liNext = (LineInfo)lineData.get(i + 1);
            if (!liNext.lineFileIDSet
                && liNext.inputStartLine == li.inputStartLine + li.inputLineCount
                && liNext.outputLineIncrement == li.outputLineIncrement
                && liNext.outputStartLine
                    == li.outputStartLine
                        + li.inputLineCount * li.outputLineIncrement) {
                li.setInputLineCount(li.inputLineCount + liNext.inputLineCount);
                lineData.remove(i + 1);
            } else {
                i++;
            }
        }
    }
