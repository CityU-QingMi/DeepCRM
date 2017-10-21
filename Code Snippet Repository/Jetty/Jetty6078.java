    protected void assertRoleRequired(int index, String description)
    {
        if (index < 0)
        {
            StringBuilder err = new StringBuilder();
            err.append("Unable to find parameter with role [");
            err.append(description).append("] in method: ");
            err.append(ReflectUtils.toString(pojo,method));
            throw new InvalidSignatureException(err.toString());
        }
    }
