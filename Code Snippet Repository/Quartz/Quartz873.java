    public void setJndiName(String jndiName) throws Exception {
        String oldName = this.jndiName;
        this.jndiName = jndiName;

        if (super.getState() == STARTED) {
            unbind(oldName);

            try {
                rebind();
            } catch (NamingException ne) {
                log.error("Failed to rebind Scheduler", ne);

                throw new SchedulerConfigException(
                        "Failed to rebind Scheduler - ", ne);
            }
        }
    }
