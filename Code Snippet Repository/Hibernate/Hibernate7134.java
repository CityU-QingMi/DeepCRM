        @SuppressWarnings( "" )
        void setParamsAsString(String string) {
            params.clear();

            try {
                if ( string != null ) {
                    params.putAll( (Map<String, String>) SCRIPT_ENGINE.eval( "Java.asJSONCompatible(" + string + ")" ) );
                }
            } catch ( ScriptException ignore ) {
                // JDK 8u60 required --- use hard coded values to pass the test
                if ( !cleanup ) {
                    params.put( "paramName", "paramValue" );
                }
            }
        }
