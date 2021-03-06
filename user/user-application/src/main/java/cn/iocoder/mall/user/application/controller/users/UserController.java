package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/users/user")
public class UserController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;

    @GetMapping("/info")
    public CommonResult<UsersUserVO> info() {
        UserBO userResult = userService.getUser(UserSecurityContextHolder.getContext().getUserId());
        return success(UserConvert.INSTANCE.convert2(userResult));
    }


    @PostMapping("/update_nickname")
    public CommonResult<Boolean> updateNickname(@RequestParam("nickname") String nickname) {
        // 组装数据
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
                .setNickname(nickname);
        // 更新昵称
        return success(userService.updateUser(userUpdateDTO));
    }
}
