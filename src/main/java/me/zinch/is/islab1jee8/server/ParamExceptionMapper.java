package me.zinch.is.islab1jee8.server;

import me.zinch.is.islab1jee8.exceptions.DeserializingException;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.server.ParamException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ParamExceptionMapper implements ExceptionMapper<ParamException> {
    @Override
    public Response toResponse(ParamException e) {
        if (e.getCause() instanceof DeserializingException) {
            return Response
                    .status(400)
                    .entity(e.getCause().getMessage())
                    .build();
        }

        if (e.getCause() instanceof ExtractorException
                && e.getCause().getCause() instanceof NumberFormatException) {
            return Response
                    .status(400)
                    .entity(e.getCause().getCause().getMessage())
                    .build();
        }

        return Response
                .status(500)
                .entity(e.getMessage())
                .build();
    }
}
