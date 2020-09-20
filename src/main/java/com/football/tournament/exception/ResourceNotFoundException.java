package com.football.tournament.exception;

/**
 * This is thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException()
    {

    }

    public ResourceNotFoundException(String message, Throwable th)
    {
        super(String.format(message, th));
    }
}
