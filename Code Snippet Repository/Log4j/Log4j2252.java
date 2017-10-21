    private void checkElementName(final String name, final boolean compact, final String str, final boolean withAttributes,
            final boolean withChildren) {
        // simple checks, don't try to be too smart here, we're just looking for the names and basic shape.
        // start
        final String startStr = withAttributes ? "<" + name + " " : "<" + name + ">";
        final int startPos = str.indexOf(startStr);
        Assert.assertTrue(str, startPos >= 0);
        // end
        final String endStr = withChildren ? "</" + name + ">" : "/>";
        final int endPos = str.indexOf(endStr, startPos + startStr.length());
        Assert.assertTrue(str, endPos >= 0);
    }
