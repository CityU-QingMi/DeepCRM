    public void fire(int typ, String trn, String tn, Object[] or,
                     Object[] nr) {

        synchronized (TriggerSample.class) {
            String ors = or == null ? "null"
                                    : StringUtil.arrayToString(or);
            String nrs = nr == null ? "null"
                                    : StringUtil.arrayToString(nr);

            out.println("----------------------------------------");
            out.println(getTriggerDescriptor(trn, typ, tn));
            out.println("old row : " + ors);
            out.println("new row : " + nrs);
            out.flush();

            if ("TRIG_TEST".equals(tn)) {
                switch (typ) {

                    case INSERT_BEFORE_ROW : {

                        // Business rule: ID shall be less than 11.
                        // (Marti DiBergi, we love you ;-)
                        // You can cast row[i] given your knowledge of what
                        // the table format is:
                        final int ID = ((Number) nr[0]).intValue();

                        doAssert(ID < 11, "ID < 11");

                        break;
                    }
                    case UPDATE_BEFORE_ROW : {

                        // Business rule:  ignore update of VALUE 'unchangable'.
                        if ("unchangable".equals(or[1])) {
                            nr[1] = or[1];    // short-circuit the update
                        }

                        // !!!Warning!!!
                        // The engine does not check the class of substituted
                        // values; it's up to you to use the correct class.
                        // For example, this will cause database curruption:
                        //  nr[1] = new Integer(5);
                        break;
                    }
                }
            }

            doAuditStep(typ, tn, ors, nrs);
        }
    }
