package com.project.moneytransfer.Enums;

import com.fasterxml.jackson.annotation.JsonProperty;

// Enum for defining gender of a person
public enum Gender {
    @JsonProperty("male")Male,
    @JsonProperty("female")Female
}
