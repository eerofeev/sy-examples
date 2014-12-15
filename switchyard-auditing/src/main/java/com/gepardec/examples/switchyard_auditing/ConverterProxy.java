package com.gepardec.examples.switchyard_auditing;

import com.gepardec.examples.switchyard_auditing.generated.converter.Convert;
import com.gepardec.examples.switchyard_auditing.generated.converter.ConvertResponse;

public interface ConverterProxy {
	ConvertResponse convert(Convert convert);
}
