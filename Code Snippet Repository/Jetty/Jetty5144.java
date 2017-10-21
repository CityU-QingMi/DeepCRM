    private String getMessage(LogRecord record)
    {
        ResourceBundle bundle = record.getResourceBundle();
        if (bundle != null)
        {
            try
            {
                return bundle.getString(record.getMessage());
            }
            catch (java.util.MissingResourceException ex)
            {
            }
        }

        return record.getMessage();
    }
