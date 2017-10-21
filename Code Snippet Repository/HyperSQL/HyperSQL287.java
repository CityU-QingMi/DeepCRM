    public String getCurrentSchema() throws BadSpecial, SqlToolError {
        requireConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = shared.jdbcConn.createStatement();
            rs = st.executeQuery("VALUES CURRENT_SCHEMA");
            if (!rs.next())
                throw new BadSpecial(SqltoolRB.no_vendor_schemaspt.getString());
            String currentSchema = rs.getString(1);
            if (currentSchema == null)
                throw new BadSpecial(
                        SqltoolRB.schemaname_retrieval_fail.getString());
            return currentSchema;
        } catch (SQLException se) {
            throw new BadSpecial(SqltoolRB.no_vendor_schemaspt.getString());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException se) {
                // Purposefully doing nothing
            } finally {
                rs = null;
            }
            if (st != null) try {
                st.close();
            } catch (SQLException se) {
                // Purposefully doing nothing
            } finally {
                st = null;
            }
        }
    }
