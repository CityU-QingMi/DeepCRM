    Result getWriteAccessResult(Session session) {

        try {
            if (targetTable != null && !targetTable.isTemp()) {
                session.checkReadWrite();
            }
        } catch (HsqlException e) {
            return Result.newErrorResult(e);
        }

        return null;
    }
