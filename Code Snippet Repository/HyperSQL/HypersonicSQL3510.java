    private void checkViewTranslationAndContent(String viewName,
            String[] columnList, String viewStatement,
            String expectedTranslatedStatement,
            Object expectedContent) throws SQLException {

        createView(viewName, columnList, viewStatement);

        String actualTranslatedStatement = getViewStatement(viewName);

        if (!actualTranslatedStatement.equals(expectedTranslatedStatement)) {
            StringBuffer message = new StringBuffer();

            message.append(viewName).append(
                "'s statement not translated as expected\n");
            message.append("original statement:\n  ").append(
                viewStatement).append('\n');
            message.append("expected translated statement:\n  ").append(
                expectedTranslatedStatement).append('\n');
            message.append("actual translated statement:\n  ").append(
                actualTranslatedStatement).append('\n');

            throw new AssertionFailedError(message.toString());
        }

        if (expectedContent instanceof Object[][]) {
            ensureTableContent(viewName, (Object[][]) expectedContent);
        }
    }
