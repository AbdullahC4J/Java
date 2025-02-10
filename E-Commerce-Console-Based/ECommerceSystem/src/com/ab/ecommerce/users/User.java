package com.ab.ecommerce.users;

/**
 * Abstract base class for all users in the e-commerce system.
 * Provides common functionality for user authentication and management.
 */
public abstract class User {
    /* The username of the user */
    private String userName;
    
    /* The password of the user */
    private String password;

    /* The password checked of the admin */
    private boolean isPasswordChecked;


    /**
     * Constructs a new User with the specified credentials.
     * 
     * @param userName The username for the user
     * @param password The password for the user
     * @throws IllegalArgumentException if credentials are invalid
     */
    public User(String userName, String password) {
        setUserName(userName);
        validatePassword(password);
        setIsPasswordChecked(false);
    }

    /**
     * Gets the username.
     * @return The username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username.
     * @param userName The new username
     * @throws IllegalArgumentException if username is invalid
     */
    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (userName.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }
        if (userName.length() > 20) {
            throw new IllegalArgumentException("Username cannot exceed 20 characters");
        }
        if (!userName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, and underscores");
        }

        this.userName = userName;
    }

    /**
     * Gets the admin password checked status.
     */
    public boolean getIsPasswordChecked() {
        return isPasswordChecked;
    }

    /**
     * Sets the password checked status.
     * @param PasswordChecked The new username
     */
    public void setIsPasswordChecked(boolean PasswordChecked) {
        isPasswordChecked = PasswordChecked;
    }


    /**
     * Verifies if the provided password matches the user's password.
     * @param password The password to verify
     * @return true if password matches, false otherwise
     */
    public boolean verifyPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    /**
     * Validates the password.
     * @param password The password to validate
     * @throws IllegalArgumentException if password is invalid
     */
    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        if (password.length() > 30) {
            throw new IllegalArgumentException("Password cannot exceed 30 characters");
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{userName='" + userName + "'}";
    }
}
    // /**
    //  * Changes the user's password.
    //  * 
    //  * @param oldPassword Current password for verification
    //  * @param newPassword New password to set
    //  * @return true if password was changed successfully, false otherwise
    //  * @throws IllegalArgumentException if new password is invalid
    //  */
    // public boolean changePassword(String oldPassword, String newPassword) {
    //     if (!verifyPassword(oldPassword)) {
    //         return false;
    //     }
    //     validatePassword(newPassword);
    //     this.password = newPassword;
    //     return true;
    // }