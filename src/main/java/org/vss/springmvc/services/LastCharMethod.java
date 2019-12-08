package org.vss.springmvc.services;

import java.util.List;

import org.springframework.util.StringUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class LastCharMethod implements TemplateMethodModelEx {
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() != 1 || StringUtils.isEmpty(arguments.get(0)))
            throw new TemplateModelException("Wrong arguments!");
        String argument = arguments.get(0).toString();
        return argument.charAt(argument.length() - 1);
    }
}
