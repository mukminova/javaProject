package ru.innopolis.util;

import ru.innopolis.service.ResourceGetter;
import ru.innopolis.service.TextCounter;
import ru.innopolis.service.TextValidator;

/**
 * Created by Li on 14.10.16.
 */
public class WordCountUtil {
    private ResourceGetter resourceGetterImpl;
    private TextValidator textValidator;
    private TextCounter textCounter;

    public WordCountUtil(ResourceGetter resourceGetterImpl, TextValidator textValidator, TextCounter textCounter) {
        this.resourceGetterImpl = resourceGetterImpl;
        this.textValidator = textValidator;
        this.textCounter = textCounter;
    }

    public ResourceGetter getResourceGetterImpl() {
        return resourceGetterImpl;
    }

    public void setResourceGetterImpl(ResourceGetter resourceGetterImpl) {
        this.resourceGetterImpl = resourceGetterImpl;
    }

    public TextValidator getTextValidator() {
        return textValidator;
    }

    public void setTextValidator(TextValidator textValidator) {
        this.textValidator = textValidator;
    }

    public TextCounter getTextCounter() {
        return textCounter;
    }

    public void setTextCounter(TextCounter textCounter) {
        this.textCounter = textCounter;
    }
}
