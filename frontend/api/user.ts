import Api from '../lib/customApi';

export async function getMyInfo() {
  const response = await Api.get<User>('/user');
  return response.data;
}

export async function getUserInfo(userSeq: number) {
  const response = await Api.get<UserProfile>(`/user/${userSeq}`);
  return response.data;
}

export async function getUserPost(userSeq: number) {
  const response = await Api.get<UserPostList>(`/user/${userSeq}/post`);
  return response.data;
}

export interface User {
  email: string;
  name: string;
  nickname: string;
  birth: string;
  height: number;
  weight: number;
  phone: string;
  image: string;
  address: string;
  login_type: string;
  no: number;
}

export interface UserProfile {
  email: string;
  name: string;
  nickname: string;
  birth: string;
  height: number;
  weight: number;
  phone: string;
  image: string;
  address: string;
  login_type: string;
  no: number;
}

export interface Writer {
  birth: string;
  email: string;
  image: string;
  name: string;
  nickname: string;
  no: number;
}

export interface UserPost {
  content: string;
  created_at: string;
  image: string;
  like_count: number;
  no: number;
  open: boolean;
  title: string;
  writer: Writer;
}

export interface UserPostList extends Array<UserPost> {}
