    public boolean start(Writer writer) {
        Boolean ifResult = (Boolean) stack.getContext().get(If.ANSWER);

        if ((ifResult == null) || (ifResult)) {
            return false;
        }

        //make the comparison
        answer = (Boolean) findValue(test, Boolean.class);

        if (answer == null) {
            answer = Boolean.FALSE;
        }
        if (answer) {
            stack.getContext().put(If.ANSWER, answer);
        }
        return answer;
    }
