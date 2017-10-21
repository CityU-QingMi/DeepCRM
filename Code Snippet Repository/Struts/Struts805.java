    public void endNode() {
        depth--;
        if (tagIsEmpty) {
            writer.write('/');
            readyForNewLine = false;
            finishTag();
            elementStack.pop();
        } else {
            finishTag();
            writer.write(CLOSE);
            writer.write(elementStack.pop());
            writer.write('>');
        }
        readyForNewLine = true;
        if (depth == 0 ) {
            writer.flush();
        }
    }
