        public Object evaluate( String expression, Class<?> type )
            throws ExpressionEvaluationException
        {
            if ( preprocessor != null )
            {
                try
                {
                    return preprocessor.preprocessValue( expression, type );
                }
                catch ( BeanConfigurationException e )
                {
                    throw new ExpressionEvaluationException( e.getMessage(), e );
                }
            }
            return expression;
        }
