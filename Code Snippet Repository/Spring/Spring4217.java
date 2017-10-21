    private void noSuccessor() {
        if (compute == FRAMES) {
            Label l = new Label();
            l.frame = new Frame();
            l.frame.owner = l;
            l.resolve(this, code.length, code.data);
            previousBlock.successor = l;
            previousBlock = l;
        } else {
            currentBlock.outputStackMax = maxStackSize;
        }
        if (compute != INSERTED_FRAMES) {
            currentBlock = null;
        }
    }
