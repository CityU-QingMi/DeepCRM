    public Employee(Integer _id, String _lastName, Integer _idTitle, String _descriptionTitle, Department _dept, String _fname) {
        setId(_id);
        setLastName(_lastName);
        Title _title = new Title();
        _title.setId(_idTitle);
        _title.setDescription(_descriptionTitle);
        setTitle(_title);
        setDepartment(_dept);
        setFirstName(_fname);
    }
