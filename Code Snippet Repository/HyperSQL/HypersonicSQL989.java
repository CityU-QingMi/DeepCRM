    public Result execute(Session session) {

        Result result;

        try {
            result = getResult(session);

            clearStructures(session);
        } catch (Throwable t) {
            clearStructures(session);

            result = Result.newErrorResult(t);
        }

        return result;
    }
