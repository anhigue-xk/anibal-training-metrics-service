package com.trainingcloud.training.unit;

import com.trainingcloud.training.Utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConstantsTest {

    @Test
    void constantsNotEmptyValue() {
        Assertions.assertNotNull(Constants.DATE_FORMAT);
    }

}
