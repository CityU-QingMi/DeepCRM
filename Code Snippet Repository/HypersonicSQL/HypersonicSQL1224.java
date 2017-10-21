    public TriggerDef(HsqlNameManager.HsqlName name, int when, int operation,
                      boolean forEach, Table table, Table[] transitions,
                      RangeVariable[] rangeVars, Expression condition,
                      String conditionSQL, int[] updateColumns,
                      String triggerClassName, boolean noWait, int queueSize) {

        this(name, when, operation, forEach, table, transitions, rangeVars,
             condition, conditionSQL, updateColumns);

        this.triggerClassName = triggerClassName;
        this.nowait           = noWait;
        this.maxRowsQueued    = queueSize;
        rowsQueued            = 0;
        pendingQueue          = new HsqlDeque();

        Class cl = null;

        try {
            cl = Class.forName(triggerClassName, true,
                               Thread.currentThread().getContextClassLoader());
        } catch (Throwable t1) {
            try {
                cl = Class.forName(triggerClassName);
            } catch (Throwable t) {}
        }

        if (cl == null) {
            valid   = false;
            trigger = new DefaultTrigger();
        } else {
            try {

                // dynamically instantiate it
                trigger = (Trigger) cl.newInstance();
            } catch (Throwable t1) {
                valid   = false;
                trigger = new DefaultTrigger();
            }
        }
    }
