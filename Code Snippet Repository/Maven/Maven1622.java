    protected void mergeNotifier( Notifier target, Notifier source, boolean sourceDominant,
                                  Map<Object, Object> context )
    {
        mergeNotifier_Type( target, source, sourceDominant, context );
        mergeNotifier_Address( target, source, sourceDominant, context );
        mergeNotifier_Configuration( target, source, sourceDominant, context );
        mergeNotifier_SendOnError( target, source, sourceDominant, context );
        mergeNotifier_SendOnFailure( target, source, sourceDominant, context );
        mergeNotifier_SendOnSuccess( target, source, sourceDominant, context );
        mergeNotifier_SendOnWarning( target, source, sourceDominant, context );
    }
