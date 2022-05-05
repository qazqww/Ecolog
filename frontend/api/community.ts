import Api from '../lib/customApi';

export async function getCommunityList() {
  const response = await Api.get<CommunityList>('/community');
  console.log(response.data);
  return response.data;
}

export async function getCommunityDetail(communitySeq: number) {
  const response = await Api.get(`/community/${communitySeq}`);
  return response.data;
}

export interface Community {
  title: string;
  no: number;
  manager: Manager;
  image: string;
}
export interface Manager {
  email: string;
  image: string;
}
export interface CommunityList extends Array<Community> {}
