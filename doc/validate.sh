#!/bin/bash

xmllint --noout --schema http://www.w3.org/2001/XMLSchema.xsd request.xsd
xmllint --noout --schema http://www.w3.org/2001/XMLSchema.xsd response.xsd

#netcat localhost 10000 < req_supervision_state.xml > resp_supervision_state.xml

xmllint --noout --schema request.xsd req_supervision_state.xml req_circuits.xml req_circuit.xml

xmllint --noout --schema response.xsd resp_supervision_state.xml resp_circuits.xml resp_circuit.xml
