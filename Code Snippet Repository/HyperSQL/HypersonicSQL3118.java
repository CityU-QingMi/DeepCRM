    static void alertClient(int severity, String message,
    String sqlStateCode, DataOutputStream hOutStream) throws IOException {
        if (sqlStateCode == null) {
            sqlStateCode = "XX000";
            // This default code means INTERNAL ERROR
        }
        if (!odbcSeverityMap.containsKey(severity)) {
            throw new IllegalArgumentException(
                "Unknown severity value (" + severity + ')');
        }
        OdbcPacketOutputStream alertPacket =
            OdbcPacketOutputStream.newOdbcPacketOutputStream();
        alertPacket.write("S" + odbcSeverityMap.get(severity));
        if (severity < ODBC_SEVERITY_NOTICE) {
            alertPacket.write("C" + sqlStateCode);
        }
        alertPacket.write("M" + message);
        alertPacket.writeByte(0);
        alertPacket.xmit((severity < ODBC_SEVERITY_NOTICE) ? 'E' : 'N',
                hOutStream);
        alertPacket.close();
    }
