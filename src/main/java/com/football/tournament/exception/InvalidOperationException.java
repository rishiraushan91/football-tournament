package com.football.tournament.exception;

/**
 * This is thrown when invalid state is given.
 */
public class InvalidOperationException extends RuntimeException
{
    private String _reason;

    public InvalidOperationException(String message, String reason)
    {
        super(message);
        _reason = reason;
    }

    public String getReason()
    {
        return _reason;
    }

}

