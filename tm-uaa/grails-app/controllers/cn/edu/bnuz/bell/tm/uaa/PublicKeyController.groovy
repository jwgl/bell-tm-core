package cn.edu.bnuz.bell.tm.uaa

class PublicKeyController {
    PublicKeyService publicKeyService

    def index() {
        renderJson publicKeyService.getKey()
    }
}
