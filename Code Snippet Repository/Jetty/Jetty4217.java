        public String getExportString()
        {
            StringBuilder sb = new StringBuilder();
            for (String variable : getEnvArray())
            {
                sb.append("export \"");
                sb.append(variable);
                sb.append("\"; ");
            }
            return sb.toString();
        }
