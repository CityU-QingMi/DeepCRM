        public String toString ()
        {
            StringBuffer sb = new StringBuffer();
            sb.append("JspPropertyGroupDescriptor:");
            sb.append(" el-ignored="+_elIgnored);
            sb.append(" is-xml="+_isXml);
            sb.append(" page-encoding="+_pageEncoding);
            sb.append(" scripting-invalid="+_scriptingInvalid);
            sb.append(" deferred-syntax-allowed-as-literal="+_deferredSyntaxAllowedAsLiteral);
            sb.append(" trim-directive-whitespaces"+_trimDirectiveWhitespaces);
            sb.append(" default-content-type="+_defaultContentType);
            sb.append(" buffer="+_buffer);
            sb.append(" error-on-undeclared-namespace="+_errorOnUndeclaredNamespace);
            for (String prelude:_includePreludes)
                sb.append(" include-prelude="+prelude);
            for (String coda:_includeCodas)
                sb.append(" include-coda="+coda);
            return sb.toString();
        }
