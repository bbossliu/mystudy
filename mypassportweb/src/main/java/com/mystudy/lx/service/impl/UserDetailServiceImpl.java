package com.mystudy.lx.service.impl;

import com.mystudy.lx.entity.TbPermission;
import com.mystudy.lx.entity.TbUser;
import com.mystudy.lx.mapper.TbPermissionDao;
import com.mystudy.lx.mapper.TbUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author dalaoban
 * @create 2020-05-30-18:55
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    TbUserDao userDao;

    @Autowired
    TbPermissionDao tbPermissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(username);
        List<TbUser> tbUsers = userDao.queryAll(tbUser);

        if(! CollectionUtils.isEmpty(tbUsers) ){
            tbUser = tbUsers.get(0);

            /*认证委托人*/
//            List<AuthenticatedPrincipal> principals = new ArrayList<>();

            /*授予的权限*/
            List<GrantedAuthority> grantedAuthorities= new ArrayList<>();

            List<TbPermission> tbPermissions = tbPermissionDao.permissionByUserId(tbUser.getId());

            if(!CollectionUtils.isEmpty(tbPermissions)){
                tbPermissions.stream().forEach((item)->{
                    grantedAuthorities.add(()->{
                        return item.getEnname();
                    });
                });
            }
            return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorities);
        }
        return null;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Stream.of("ss".getBytes()).forEach(System.out::println);

        byte[] bytes = "ss".getBytes();



        MessageDigest md = MessageDigest.getInstance("sha1");
        //加密
        byte[] digest = md.digest("abc".getBytes());
        char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        //处理加密结果
        for(byte b: digest){
            sb.append(chars[(b>>4)&15]);
            sb.append(chars[b&15]);
        }
        System.out.println(sb.toString());
        System.out.println("--------------");
        System.out.println(digest.length);
        System.out.println("==========");
        byte[] bytes1 = "abc".getBytes();

        for(byte b: bytes1){
            System.out.println(b);
        }

    }
}
