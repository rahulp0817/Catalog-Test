package com.shamir.factory;

import com.shamir.core.SecretSharing;
import com.shamir.core.ShamirSecretSharingImplementation;

public class SecretSharingFactory {
    private static SecretSharing instance;

    public static SecretSharing createShamirImplementation() {
        if (instance == null) {
            instance = new ShamirSecretSharingImplementation();
        }
        return instance;
    }
}
