menuGroup 'main', {
    affair 40, {
        workItems    10, 'PERM_WORK_ITEMS',   '/web/core/users/${userId}/works'
        '---'        20
    }
}

menuGroup 'user', {
    profile 10, {
        modify       10, 'PERM_PROFILE_SETUP', '/web/core/users/${userId}/profile'
        password     20, 'PERM_PROFILE_SETUP', '/web/core/users/${userId}/password'
    }
}