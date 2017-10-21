    public TriggerPropertyBundle loadExtendedTriggerProperties(Connection conn, TriggerKey triggerKey) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(Util.rtp(SELECT_SIMPLE_PROPS_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, triggerKey.getName());
            ps.setString(2, triggerKey.getGroup());
            rs = ps.executeQuery();
    
            if (rs.next()) {
                SimplePropertiesTriggerProperties properties = new SimplePropertiesTriggerProperties();
                    
                properties.setString1(rs.getString(COL_STR_PROP_1));
                properties.setString2(rs.getString(COL_STR_PROP_2));
                properties.setString3(rs.getString(COL_STR_PROP_3));
                properties.setInt1(rs.getInt(COL_INT_PROP_1));
                properties.setInt2(rs.getInt(COL_INT_PROP_2));
                properties.setLong1(rs.getInt(COL_LONG_PROP_1));
                properties.setLong2(rs.getInt(COL_LONG_PROP_2));
                properties.setDecimal1(rs.getBigDecimal(COL_DEC_PROP_1));
                properties.setDecimal2(rs.getBigDecimal(COL_DEC_PROP_2));
                properties.setBoolean1(rs.getBoolean(COL_BOOL_PROP_1));
                properties.setBoolean2(rs.getBoolean(COL_BOOL_PROP_2));
                
                return getTriggerPropertyBundle(properties);
            }
            
            throw new IllegalStateException("No record found for selection of Trigger with key: '" + triggerKey + "' and statement: " + Util.rtp(SELECT_SIMPLE_TRIGGER, tablePrefix, schedNameLiteral));
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(ps);
        }
    }
