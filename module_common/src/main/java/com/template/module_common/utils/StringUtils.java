package com.template.module_common.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关工具类
 */
public class StringUtils {

    //数字
    private static final String REG_NUMBER = ".*\\d+.*";
    //小写字母
    private static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //大写字母
    private static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号
    private static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";


    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 字符串拼接,线程安全
     */
    public static String buffer(String... array) {
        StringBuffer s = new StringBuffer();
        for (String str : array) {
            s.append(str);
        }
        return s.toString();
    }

    /**
     * 字符串拼接,线程不安全,效率高
     */
    public static String builder(String... array) {
        StringBuilder s = new StringBuilder();
        for (String str : array) {
            s.append(str);
        }
        return s.toString();
    }


    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b)
            return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0)))
            return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0)))
            return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1)
            return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s))
            return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s))
            return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 验证手机格式
     */
/*    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][345678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }*/

    /**
     * 删除h5标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        if (!TextUtils.isEmpty(htmlStr)) {
            // 含html标签的字符串
            String textStr = "";
            Pattern p_script;
            Matcher m_script;
            Pattern p_style;
            Matcher m_style;
            Pattern p_html;
            Matcher m_html;
            Pattern p_space;
            Matcher m_space;
            Pattern p_escape;
            Matcher m_escape;

            try {
                // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
                String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
                // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
                String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
                // 定义HTML标签的正则表达式
                String regEx_html = "<[^>]+>";
                // 定义空格回车换行符
                String regEx_space = "\\s*|\t|\r|\n";
                // 定义转义字符
                String regEx_escape = "&.{2,6}?;";
                // 过滤script标签
                p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
                m_script = p_script.matcher(htmlStr);
                htmlStr = m_script.replaceAll("");
                // 过滤style标签
                p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
                m_style = p_style.matcher(htmlStr);
                htmlStr = m_style.replaceAll("");
                // 过滤html标签
                p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
                m_html = p_html.matcher(htmlStr);
                htmlStr = m_html.replaceAll("");
                // 过滤空格回车标签
                p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
                m_space = p_space.matcher(htmlStr);
                htmlStr = m_space.replaceAll("");
                // 过滤转义字符
                p_escape = Pattern.compile(regEx_escape, Pattern.CASE_INSENSITIVE);
                m_escape = p_escape.matcher(htmlStr);
                htmlStr = m_escape.replaceAll("");
                textStr = htmlStr;
            } catch (Exception e) {
            }
            return textStr;
        } else {
            return "";
        }
    }

    /**
     * 格式化
     *
     * @param count
     * @return
     */
    public static String formatCount(int count) {
        if (count < 1000 && count > 0) {
            return String.valueOf(count);
        } else if (count >= 1000) {
            return format(count / 1000) + "k";
        }
        return "0";
    }


    public static String format(double count) {
        return String.format("%.2f", count);
    }


    /**
     * 校验密码中是否包含 字母和数字
     *
     * @param password
     * @return
     */
    public static boolean checkPasswordRule(String password) {
        if (password == null || password.length() < 6 || password.length() > 14) {
            return false;
        }
        if (password.matches(REG_NUMBER) && (password.matches(REG_LOWERCASE) || password.matches(REG_UPPERCASE))) {
            return true;
        }
        return false;
    }


    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length, int size) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    /**
     * 校验字符串是否完全由数字组成
     *
     * @param str
     * @return
     */
    public static boolean isStrComposedOfNumbers(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.matches("[0-9]+");
        }
        return false;
    }


    /*public static void setSpecifiedText(TextView textView, String specifiedText) {
        String[] keyword = new String[]{specifiedText};
        SpannableStringBuilder spannable = new SpannableStringBuilder(textView.getText().toString());
        CharacterStyle span;
        String wordReg;
        for (int i = 0; i < keyword.length; i++) {
            String key = "";
            //  处理通配符问题
            if (keyword[i].contains("*") || keyword[i].contains("(") || keyword[i].contains(")")) {
                char[] chars = keyword[i].toCharArray();
                for (int k = 0; k < chars.length; k++) {
                    if (chars[k] == '*' || chars[k] == '(' || chars[k] == ')') {
                        key = key + "\\" + String.valueOf(chars[k]);
                    } else {
                        key = key + String.valueOf(chars[k]);
                    }
                }
                keyword[i] = key;
            }

            wordReg = "([?i])" + keyword[i];   //忽略字母大小写
            Pattern pattern = Pattern.compile(wordReg);
            Matcher matcher = pattern.matcher(textView.getText().toString());
            while (matcher.find()) {
                span = new ForegroundColorSpan(Color.parseColor("#4385F4"));
                spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_MARK_MARK);
            }
        }
        textView.setText(spannable);
    }*/


    /*public static void setSpecifiedText(TextView textView, String content, String keyWord, String searchColor) {
        if (TextUtils.isEmpty(content)) {
            textView.setText("");
            return;
        }
        if (TextUtils.isEmpty(keyWord)) {
            textView.setText(content);
            return;
        }
        String[] keyword = new String[]{keyWord};
        SpannableStringBuilder spannable = new SpannableStringBuilder(content);
        CharacterStyle span;
        String wordReg;
        for (int i = 0; i < keyword.length; i++) {
            String key = "";
            //  处理通配符问题
            if (keyword[i].contains("*") || keyword[i].contains("(") || keyword[i].contains(")")) {
                char[] chars = keyword[i].toCharArray();
                for (int k = 0; k < chars.length; k++) {
                    if (chars[k] == '*' || chars[k] == '(' || chars[k] == ')') {
                        key = key + "\\" + String.valueOf(chars[k]);
                    } else {
                        key = key + String.valueOf(chars[k]);
                    }
                }
                keyword[i] = key;
            }

            wordReg = "(?i)" + keyword[i];   //忽略字母大小写
            Pattern pattern = Pattern.compile(wordReg);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                span = new ForegroundColorSpan(Color.parseColor(searchColor));
                spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_MARK_MARK);
            }
        }
        textView.setText(spannable);
    }
*/

    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return 结果SpannableString
     */
    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        SpannableString s = new SpannableString(text);
        keyword = escapeExprSpecialWord(keyword);
        text = escapeExprSpecialWord(text);
        if (text.contains(keyword) && !TextUtils.isEmpty(keyword)) {
            try {
                Pattern p = Pattern.compile(keyword);
                Matcher m = p.matcher(s);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (Exception e) {
            }
        }
        return s;
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return keyword
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }


    /**
     * @param checkType 校验类型：0校验手机号码，1校验座机号码，2两者都校验满足其一就可
     * @param phoneNum
     */
    public static boolean validPhoneNum(String checkType, String phoneNum) {
        boolean flag = false;
        Pattern p1 = null;
        Pattern p2 = null;
        Matcher m = null;
        p1 = Pattern.compile("^(((11[0-9]{1})|(12[0-9]{1})|(13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})" +
                "|(16[0-9]{1})|(19[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");
        p2 = Pattern.compile("^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$");
        if ("0".equals(checkType)) {
            System.out.println(phoneNum.length());
            if (phoneNum.length() != 11) {
                return false;
            } else {
                m = p1.matcher(phoneNum);
                flag = m.matches();
            }
        } else if ("1".equals(checkType)) {
            if (phoneNum.length() < 11 || phoneNum.length() >= 16) {
                return false;
            } else {
                m = p2.matcher(phoneNum);
                flag = m.matches();
            }
        } else if ("2".equals(checkType)) {
            if (!((phoneNum.length() == 11 && p1.matcher(phoneNum).matches()) || (phoneNum.length() < 16 && p2.matcher(phoneNum).matches()))) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

}