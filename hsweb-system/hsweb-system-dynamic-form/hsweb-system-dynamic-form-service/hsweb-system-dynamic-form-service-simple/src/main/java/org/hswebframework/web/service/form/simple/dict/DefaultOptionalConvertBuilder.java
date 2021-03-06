package org.hswebframework.web.service.form.simple.dict;

import org.hswebframework.ezorm.core.OptionConverter;
import org.hswebframework.web.entity.form.DictConfig;
import org.hswebframework.web.service.form.OptionalConvertBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouhao
 * @since 3.0
 */
@Component
public class DefaultOptionalConvertBuilder implements OptionalConvertBuilder {

    @Autowired(required = false)
    private List<OptionalConvertBuilderStrategy> strategies;

    @Override
    public OptionConverter build(DictConfig dictConfig) {
        return strategies.stream()
                .filter(strategy -> strategy.support(dictConfig.getType()))
                .findFirst()
                .map(strategy -> strategy.build(dictConfig))
                .orElse(null);
    }
}
