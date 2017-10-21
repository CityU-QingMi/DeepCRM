    public String[] getInitialSchemaSQL() {

        HsqlArrayList list = new HsqlArrayList(userList.size());

        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);

            if (user.isSystem) {
                continue;
            }

            HsqlName name = user.getInitialSchema();

            if (name == null) {
                continue;
            }

            list.add(user.getInitialSchemaSQL());
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
