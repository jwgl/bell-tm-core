package cn.edu.bnuz.bell.profile

class ProfileController {
    ProfileService profileService

    def show(String userId) {
        respond profileService.getUserInfo(userId)
    }

    def update(String userId) {
        def cmd = new ProfileCommand()
        bindData(cmd, request.JSON)
        this.profileService.update(userId, cmd)

        renderOk()
    }
}
