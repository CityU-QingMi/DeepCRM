    public static Constraint createConstraint(Constraint constraint)
    {
        try
        {
            return (Constraint)constraint.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new IllegalStateException (e);
        }
    }
