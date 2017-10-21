        public void fetchRoles() throws Exception
        {
            this.user.fetchRoles();
            this.roles = new ArrayList<JAASRole>();
            if (this.user.getRoleNames() != null)
            {
                Iterator<String> itor = this.user.getRoleNames().iterator();
                while (itor.hasNext())
                    this.roles.add(new JAASRole((String)itor.next()));
            }
        }
